const showModal = (messageType, messageContent) => {
  $('#modalTitle').text(messageType);
  $('#modalContent').text(messageContent);
  if (messageType === '오류 메시지') $('#messageModal').addClass('bg-warning');
  else $('#messageModal').addClass('bg-success');
  $('#modal').modal('show');
};

