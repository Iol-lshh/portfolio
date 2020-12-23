const searchForm = $('#searchForm');
let params = null;

const handleLoadedLink = () => {
  const contextPath = location.pathname.substring(0, location.pathname.indexOf('/', 1));
  const link = `${contextPath}/post/write.do${location.search}`;
  $('#writeBtn').attr('href', link)  ;
}

const handleInputChange = () => {
  search.focus();
};

const handleSearchSubmit = (e) => {
  e.preventDefault();
  if($('#startDate').val() || $('#endDate').val()) {
	if(!$('#startDate').val() || !$('#endDate').val()) {
		sessionStorage.setItem('messageType', '오류 메시지');
		sessionStorage.setItem('messageContent', '시작 날짜와 끝 날짜를 모두 입력해 주세요.');
		location.href = `board_list.do${location.search}`;
		return;
	}
}
  let { root, type, page } = params;
  page = 1;
  let url = `root=${root}&type=${type}&page=${page}`;
  if($('#startDate').val() && $('#endDate').val()) 
	url += `&startDate=${$('#startDate').val()}&endDate=${$('#endDate').val()}`;

  if($('#searchType').val() && $('#search').val()) 
	url += `&searchType=${$('#searchType').val()}&search=${$('#search').val()}`;

  location.href = `board_list.do?${url}`;
};

const handleClickSubmit = () => {
  searchForm.submit();
};

const handleClickSearch = () => {
  $('#modalForm').modal('show');
  search.focus();
};

const loadedSearchType = () => {
  const { searchType } = params;
  if (searchType) {
    const options = $('#searchType').children();
    Array.from(options).forEach((option) => {
      option.value === searchType
        ? (option.selected = true)
        : (option.selected = false);
    });
  }
};

const handledType = () => {
const btns = document.querySelectorAll('.actionBtn');
  switch (params.type) {
    case 'list':
      btns[0].classList.add('text-primary');
      return;
    case 'album':
      btns[1].classList.add('text-primary');
      return;
    case 'post':
      btns[2].classList.add('text-primary');
      return;
    default:
      btns[0].classList.add('text-primary');
      return;
  }
};

const aa = (e) => {
	console.log(e.target);
	console.log(e.target.href)
}

export const initActionbar = (initialParams) => {
  params = initialParams;
  handledType();
  window.addEventListener('load', loadedSearchType);
  $('#searchBtn').on('click', handleClickSearch);
  $('#searchSubmitBtn').on('click', handleClickSubmit);
  searchForm.on('submit', handleSearchSubmit);
  $('#searchType').on('change', handleInputChange);
  $(window).on('load', handleLoadedLink)
};
