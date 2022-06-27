export function logout(contextPath, username, tokenName, tokenValue) {
  var conpath = contextPath.substring(0, contextPath.indexOf('/', 2));
  var result = '';
  result += '<form id="logoutForm"';
  result += ' method="POST"';
  result += ' action="' + conpath + '/logoutProcess"';
  result += ' calss="hide"';
  result += '>';
  result +=
    ' <input type="hidden"  name="um_username" value="' + username + '" />';
  result += ' <input type="hidden"';
  result += ' ';
  result += ' name="' + tokenName + '"';
  result += ' value="' + tokenValue + '"';
  result += ' />';
  result += ' </form>';

  return result;
}
