import { initParams } from './handleParams.js';
import { initActionbar } from './actionBar.js';
import { getDate } from './ajax.js';
import { initAjaxScroll } from './ajaxScroll.js';
import { showModal } from './modal.js';

showModal();

const params = initParams();

initActionbar(params);

getDate(params);
if (params.type === 'post') initAjaxScroll(params);
