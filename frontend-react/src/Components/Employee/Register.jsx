import React, { useState } from "react";
import { getJSONRequestData, REGISTER_URL } from "../../Services/ApiService";
import {
  areBothPasswordsEqual,
  isValidEmail,
  isValidEmployeeId,
  isValidName,
  isStrongPassword,
  isValidPhoneNumber,
} from "../../Services/FormValidation";

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

    // form input validation
    if (
      isValidName(name) &&
      isValidEmployeeId(employeeId) &&
      isValidPhoneNumber(phoneNumber) &&
      isValidEmail(email) &&
      isStrongPassword(password) &&
      areBothPasswordsEqual(password, password2)
    ) {
      let employeeRegistrationData = {
        name: name,
        emailId: email,
        password: password,
        phoneNumber: phoneNumber,
        employeeId: employeeId,
      };

      //post request to backend API using fetch call
      fetch(REGISTER_URL, getJSONRequestData(employeeRegistrationData))
        .then(async (resp) => {
          //body of response
          const data = await resp.json();

          // CREATED
          if (resp.status === 201) {
            props.navigate("/registrationSuccess");
            return;
          }

          // UNPROCESSABLE ENTITY
          if (resp.status === 422) {
            props.setTitle(data.description);
          }
          // VIOLATED INPUT FORM
          else if (resp.status === 400) {
            let violations = "";
            for (const violation of data.violations) {
              violations = violations.concat(violation.message, ". ");
            }

            props.setTitle(violations.trim());
          }
          // ANY OTHER CASE
          else {
            props.setTitle("Something went wrong...");
          }

          props.navigate("/registrationFailure");
        })
        .catch((err) => {
          window.alert(
            "Something went wrong.... Please try again after some time"
          );
        });
    } else {
      setPassword("");
      setPassword2("");
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
                onBlur={(e) => setName(e.target.value.trim())}
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
                onBlur={(e) => setEmployeeId(e.target.value.trim())}
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
                onBlur={(e) => setPhoneNumber(e.target.value.trim())}
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
                onBlur={(e) => setEmail(e.target.value.trim())}
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
                Your password must be atleast 8 characters and must be strong.
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
