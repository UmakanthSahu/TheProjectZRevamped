import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getJSONRequestData, LOGIN_URL } from "../../Services/ApiService";
import { isValidEmail, isStrongPassword } from "../../Services/FormValidation";

export const Login = (props) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const onSubmitLoginHandler = (event) => {
    event.preventDefault();

    // validation of form input
    if (isValidEmail(email) && isStrongPassword(password)) {
      let employeeLoginData = {
        emailId: email,
        password: password,
      };

      //connecting with backend
      fetch(LOGIN_URL, getJSONRequestData(employeeLoginData))
        .then(async (resp) => {
          
          // 200 OK LOGIN SUCCESS
          if (resp.status === 200) {
            props.setAuthorizedLogin(email);
            navigate("/dashboard");
            return;
          }

          const data = await resp.json();
          // UNAUTHORIZED
          if (resp.status === 401) {
            props.setTitle(data.description);
          }
          // VIOLATED INPUT FORM
          else if (resp.status === 400) {
            let violations = "";
            for (const violation of data.violations) {
              violations = violations.concat(violation.message, ". ");
            }

            props.setTitle(violations);
          }
          //ANY OTHER CASE
          else {
            props.setTitle("Something went wrong...");
          }
          navigate("/invalidCredentials");
        })
        .catch((err) => {
          window.alert(
            "Something went wrong.... Please try again after some time"
          );
          console.log(err);
        });
    } else {
      setPassword("");
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
                onBlur={(e) => setEmail(e.target.value.trim())}
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
