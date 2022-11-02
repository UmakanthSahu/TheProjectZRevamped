export function isValidEmail(email) {
  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
    return true;
  }
  window.alert("Please enter a valid email!");
  return false;
}

export function isValidPasswordLength(password) {
  if (password.length >= 8) {
    return true;
  }
  window.alert("Password should be greater than 7 characters");
  return false;
}

export function areBothPasswordsEqual(password, passwordAgain) {
  if (password === passwordAgain) {
    return true;
  }
  window.alert("Passwords didnot match. Please enter your password again");
  return false;
}
