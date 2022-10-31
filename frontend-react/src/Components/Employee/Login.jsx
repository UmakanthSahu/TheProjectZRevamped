import React, { useState } from "react";
import { useEffect } from "react";
import { getJSONRequestData, LOGIN_URL } from "../../Services/ApiService";

export const Login = (props) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if (props.authorizedLogin !== "") {
      props.navigate("/dashboard");
    }
  });

  const onSubmitLoginHandler = (event) => {
    event.preventDefault();
    if (password.length < 8) {
      alert(
        "Password must be greater than 8 characters. Re-enter your password."
      );
      setPassword("");
    } else {
      let employeeLoginData = {
        emailId: email,
        password: password,
      };
      console.log("fine", employeeLoginData);

      fetch(LOGIN_URL, getJSONRequestData(employeeLoginData))
        .then((resp) => {
          console.log(resp);
          if (resp.status === 200) {
            props.setAuthorizedLogin(email);
            props.navigate("/dashboard");
          } else {
            props.navigate("/invalidCredentials");
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
          <h3 className="card-title text-center">Login</h3>
          <form onSubmit={onSubmitLoginHandler}>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email address
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                aria-describedby="emailHelp"
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter your Email Address"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter your Password"
              />
              <div id="passwordHelpBlock" className="form-text">
                Your password must be atleast 8 characters long.
              </div>
            </div>

            <button type="submit" className="btn btn-primary mx-auto">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};
