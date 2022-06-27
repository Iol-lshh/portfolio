import { initPagination } from './pagination.js';

let params = null;
const url = 'board_list.ajax?';
let query = null;
let contextPath = null;

const createList = (json) => {
  if (json.data === null) return;
  let table = '';
  json.data.forEach((item) => {
    table += '<tr>';
    table += `<td class="text-center table__no">${item.postId}</td>`;
	let title = item.title;
	
	if(title.length > 20) {
	  title = title.substring(0, 20);
	  title += '...';
	} 
    table += `<td class="text-center table__title"><a class="list__link" href="${contextPath}/post/view.do?${query}&post_id=${item.postId}">${title}`;
	const today = new Date();
	const itemDay = new Date(item.regdate);
	if(today.getFullYear() === itemDay.getFullYear()) {
		if(today.getMonth() === itemDay.getMonth()) {
			if(today.getDate() === itemDay.getDate()) table += '&nbsp;<span class="badge badge-pill badge-danger">N</span>';
		}
	} 
	table += `</a></td>`;
    table += `<td class="text-center table__user">${item.nickName}</td>`;
    table += `<td class="text-center table__date">${item.regdate}</td>`;
    table += `<td class="text-center table__views">${item.viewcnt}</td>`;
    table += `<td class="text-center table__hit">${item.empathizeCnt}</td>`;
    table += '</tr>';
  });
  $('#jsonList').html(table);
};

const createcards = (json) => {
  if (json.data === null) return;
  let post = '';
  json.data.forEach((item) => {
    const img = item.thumbnailPath
      ? item.thumbnailPath
      : '../images/default_image.png';
    post += `<div class="card-wrapper" onclick=location.href="${contextPath}/post/view.do?${query}&post_id=${item.postId}">`;
    post += `<div class="card">`;
    post += `<div class="card-body">`;
 	
	let title = item.title;
	
	if(title.length > 7) {
	  title = title.substring(0, 7);
	  title += '...';
	} 
	
    post += `<h5 class="card-title">${title}`;
	const today = new Date();
	const itemDay = new Date(item.regdate);
    if(today.getFullYear() === itemDay.getFullYear()) {
	  if(today.getMonth() === itemDay.getMonth()) {
		if(today.getDate() === itemDay.getDate()) post += '&nbsp;<span class="badge badge-pill badge-danger">N</span>';
	  }
	}
	post += `</h5>`;
    post += `<div class="card-user">${item.nickName}</div>`;
    post += `<div class="card-text">${item.contentsText}</div>`;
    post += `<div class="info__boardDate">${item.regdate}</div>`;
    post += `<span class="info__boardViews">`;
    post += `<i class="far fa-eye"></i>`;
    post += `<span>${item.viewcnt}</span>`;
    post += `</span>`;
    post += `&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`;
    post += `<span class="info__boardHits">`;
    post += `<i class="far fa-thumbs-up"></i>`;
    post += `<span>${item.empathizeCnt}</span>`;
    post += `</span>`;
    post += `</div>`;
    post += `<div class="card-img-top">`;
    post += `<img src=${img} alt="userImage">`;
    post += `</div>`;
    post += `</div>`;
    post += `</div>`;
  });

if(params.type === 'post') $('#jsonPost').html(post);
else $('#jsonAlbum').html(post);
};

const requestAjax = () => {
  $('#loading').removeClass('hide');

  fetch(url + query)
    .then((response) => response.json())
    .then((json) => {
      let { type } = params;
      type = type.toLowerCase();
      switch (type) {
        case 'list':
          createList(json);
          break;
        case 'album':
          createcards(json);
          break;
        case 'post':
          createcards(json);
          break;
      }
      if (type !== 'post') initPagination(params, json.count);
      $('#loading').addClass('hide');
      $('#listContent').removeClass('hide');
    })
    .catch((e) => {
      sessionStorage.setItem('messageType', '오류 메시지');
      sessionStorage.setItem('messageContent', '접근할수 없습니다.');
      sessionStorage.setItem('error', e.message);
      history.back();
    });
};

export const getContextPath = () => {
  const hostIndex = location.href.indexOf(location.host) + location.host.length;
  return location.href.substring(
    hostIndex,
    location.href.indexOf('/', hostIndex + 1)
  );
};

export const createQuery = () => {
  let i = 0;
  let query = '';

  for (let key in params) {
    if (params[key]) {
      if (i === 0) {
        query += key + '=';
        query += params[key];
        i++;
        continue;
      }
      query += '&' + key + '=';
      query += params[key];
    }
  }

  return query;
};

export const getDate = (initialParams) => {
  params = initialParams;
  query = createQuery();
  contextPath = getContextPath();
  requestAjax();
};
