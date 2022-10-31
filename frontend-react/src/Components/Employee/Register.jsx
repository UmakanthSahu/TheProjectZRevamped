import React, { useState } from "react";
import { getJSONRequestData, REGISTER_URL } from "../../Services/ApiService";

export const Register = (props) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [password2, setPassword2] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [employeeId, setEmployeeId] = useState("");

  // runs when registration form is submitted
  const onSubmitRegisterHandler = async (event) => {
    event.preventDefault();

    // check if password is valid or not
    if (password.length < 8 || password2.length < 8) {
      alert("Password must be greater than 8 characters.");
      setPassword("");
      setPassword2("");
    } else if (password !== password2) {
      alert("passwords donot match. Re-enter.");
      setPassword("");
      setPassword2("");
    } else {
      // if valid then register using post request to backend API using fetch call
      let employeeRegistrationData = {
        name: name,
        emailId: email,
        password: password,
        phoneNumber: phoneNumber,
        employeeId: employeeId,
      };

      fetch(REGISTER_URL, getJSONRequestData(employeeRegistrationData))
        .then((resp) => {
          console.log(resp);
          if (resp.status === 200) {
            props.navigate("/registrationSuccess");
          } else {
            props.navigate("/registrationFailure");
          }
        })
        .catch((err) => {
          console.log(err);
          window.alert("Something went wrong.... Please try after some time");
        });
    }
  };

  return (
    <div className="outer">
      <div className="card">
        <div className="card-body">
          <h3 className="card-title text-center">Register</h3>
          <form onSubmit={onSubmitRegisterHandler}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name:
              </label>
              <input
                type="name"
                className="form-control"
                id="name"
                name="name"
                placeholder="Enter your Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="employeeId" className="form-label">
                Employee Id:
              </label>
              <input
                type="number"
                className="form-control"
                id="emloyeeId"
                name="employeeId"
                placeholder="Enter your Employee Id"
                value={employeeId}
                onChange={(e) => setEmployeeId(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phoneNumber" className="form-label">
                Phone Number:
              </label>
              <input
                type="tel"
                className="form-control"
                id="phoneNumber"
                name="phoneNumber"
                placeholder="Enter your Phone Number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email address:
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                placeholder="Enter your Email Address"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password:
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                placeholder="Enter your Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
              <div id="passwordHelpBlock" className="form-text">
                Your password must be atleast 8 characters long.
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password Again:
              </label>
              <input
                type="password"
                className="form-control"
                id="password2"
                placeholder="Enter your Password again"
                value={password2}
                onChange={(e) => setPassword2(e.target.value)}
                required
              />
              <div id="passwordHelpBlock" className="form-text">
                Your passwords must match.
              </div>
            </div>

            <button type="submit" className="btn btn-primary mx-auto">
              Register
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};
