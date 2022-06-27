let params = null;
let maxPagination = null;

const createPaginationIcon = (className) => {
  const i = document.createElement('i');
  i.className = className;
  return i;
};

const createPaginationAnchor = (link) => {
  const a = document.createElement('a');
  a.className = 'page-link';
  a.href = link;
  a.setAttribute('tabindex', '-1');
  a.setAttribute('aria-disabled', 'true');
  return a;
};

const createPaginationList = (className) => {
  const li = document.createElement('li');
  li.className = className;
  return li;
};

const handledPagination = () => {
  if (params.type === 'post') return;
  const pagination = document.getElementById('pagination');
  let { root, type, page, searchType, search } = params;
  page = parseInt(page);
  let url = '';
  if (searchType && search) {
    url += `root=${root}&type=${type}&searchType=${searchType}&search=${search}`;
  } else {
    url += `root=${root}&type=${type}`;
  }

  const startPagination = Math.floor((page - 1) / 10) * 10 + 1;
  // let endPagination = (Math.floor((page - 1) / 10) + 1) * 10;
  let endPagination = startPagination + 9;
  if (endPagination > maxPagination) endPagination = maxPagination;

  if (startPagination === 1) {
    const li = createPaginationList('page-item disabled');
    const i = createPaginationIcon('fas fa-angle-double-left');
    const a = createPaginationAnchor('#');
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  } else {
    const li = createPaginationList('page-item');
    const i = createPaginationIcon('fas fa-angle-double-left');
    const a = createPaginationAnchor(`board_list.do?${url}&page=${1}`);
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  }
  if (startPagination === 1) {
    const li = createPaginationList('page-item disabled');
    const i = createPaginationIcon('fas fa-chevron-left');
    const a = createPaginationAnchor('#');
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  } else {
    const li = createPaginationList('page-item');
    const i = createPaginationIcon('fas fa-chevron-left');
    const a = createPaginationAnchor(`board_list.do?${url}&page=${(Math.floor((page - 1) / 10) - 1) * 10 + 1}`);
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  }

  for (let i = startPagination; i <= endPagination; i++) {
    let li = createPaginationList('page-item');
    if (i === page) li.classList.add('active');
    const a = createPaginationAnchor(`board_list.do?${url}&page=${i}`);
    a.innerText = i;
    li.appendChild(a);
    pagination.appendChild(li);
  }

  if (endPagination === maxPagination) {
    const li = createPaginationList('page-item disabled');
    const i = createPaginationIcon('fas fa-chevron-right');
    const a = createPaginationAnchor('#');
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  } else {
    const li = createPaginationList('page-item a');
    const i = createPaginationIcon('fas fa-chevron-right');
    const a = createPaginationAnchor(`board_list.do?${url}&page=${(Math.floor((page - 1) / 10 ) + 1) * 10 + 1}`);
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  }
  if (endPagination === maxPagination) {
    const li = createPaginationList('page-item disabled');
    const i = createPaginationIcon('fas fa-angle-double-right');
    const a = createPaginationAnchor('#');
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  } else {
    const li = createPaginationList('page-item b');
    const i = createPaginationIcon('fas fa-angle-double-right');
    const a = createPaginationAnchor(`board_list.do?${url}&page=${maxPagination}`);
    a.appendChild(i);
    li.appendChild(a);
    pagination.appendChild(li);
  }
};

export const initPagination = (initialParams, datalength) => {
  params = initialParams;
  if(params.type === 'list')
    maxPagination = Math.ceil(datalength / 10);
  else 
    maxPagination = Math.ceil(datalength / 8);
  handledPagination();
};
