const messageType = sessionStorage.getItem('messageType');
const messageContent = sessionStorage.getItem('messageContent');

export const showModal = () => {
  if (messageType || messageContent) {
    $('#modalTitle').text(messageType);
    $('#modalContent').text(messageContent);
    if (messageType === '오류 메시지')
      $('#messageModal').addClass('bg-warning');
    else $('#messageModal').addClass('bg-success');
    $('#modal').modal('show');
    // console.error(sessionStorage.getItem('error'));
    sessionStorage.removeItem('messageType');
    sessionStorage.removeItem('messageContent');
    sessionStorage.removeItem('error');
  }
};

export const modalControl = (messageType, messageContent) => {
  $('#modalTitle').text(messageType);
  $('#modalContent').text(messageContent);
  if (messageType === '오류 메시지') $('#messageModal').addClass('bg-warning');
  else $('#messageModal').addClass('bg-success');
  $('#modal').modal('show');
};
