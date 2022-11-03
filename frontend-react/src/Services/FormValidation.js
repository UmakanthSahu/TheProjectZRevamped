export function isValidEmail(email) {
  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
    return true;
  }
  window.alert("Please enter a valid email!");
  return false;
}

export function isValidPasswordLength(password) {
  if (password.length >= 8 && password.length <= 50) {
    return true;
  }
  window.alert(
    "Password should be greater than 7 characters and less than 51 characters"
  );
  return false;
}

export function areBothPasswordsEqual(password, passwordAgain) {
  if (password === passwordAgain) {
    return true;
  }
  window.alert("Passwords didnot match. Please enter your password again");
  return false;
}

export function isValidName(name) {
  if (name.length > 40) {
    window.alert("name should not be greater than 40 characters");
    return false;
  }else{
    return true;
  }

  // if (/^[A-Za-z\\s]{2,40}$/i.test(name)) {
  //   return true;  
  // }

  // window.alert("Invalid name. Please enter your name again");
  // return false;
}

export function isValidPhoneNumber(phoneNumber) {
  if (/^[0-9]{9,15}$/.test(phoneNumber)) {
    return true;
  }

  window.alert("Phone Number cannot be less than 9 digits greater than 16 digits");
  return false;
}

export function isValidEmployeeId(employeeId) {
  if (employeeId.length <= 10) {
    return true;
  }
  window.alert("Employee Id cannot be greater than 10 digits");
  return false;
}
