async function checkCookies() {
  var auth = await cookieStore.get('Authorization');
  var userId = await cookieStore.get('UserId');
  var role = await cookieStore.get('Role');
  if(auth == null || userId == null || role == null) {
    await cookieStore.delete('Authorization');
    await cookieStore.delete('UserId');
    await cookieStore.delete('Role');
    await cookieStore.delete('isExpired')
  }
}
checkCookies();