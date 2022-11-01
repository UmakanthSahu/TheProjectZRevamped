import "./App.css";
import { Routes, Route, useNavigate } from "react-router-dom";
import { Navbar } from "./Components/Common/Navbar";
import { Home } from "./Components/Common/Home";
import { Login } from "./Components/Employee/Login";
import { Register } from "./Components/Employee/Register";
import { Dashboard } from "./Components/Employee/Dashboard";
import { RedirectWithMessage } from "./Components/Common/RedirectWithMessage";
import { useState } from "react";

function App() {
  // when user is logged in, this state contains the email id
  const [authorizedLogin, setAuthorizedLogin] = useState("");
  const [title, setTitle] = useState("");
  const navigate = useNavigate();

  // handles logout by clearing authorizedLogin
  const logoutHandler = (event) => {
    event.preventDefault();
    if (window.confirm("Are you sure want to logout?")) {
      setAuthorizedLogin("");
      navigate("/logoutSuccess");
    }
  };

  return (
    <div className="App">
      <Navbar authorizedLogin={authorizedLogin} logoutHandler={logoutHandler} />
      <div className="my-3">
        <Routes>
          <Route
            exact
            path="/login"
            element={
              <Login
                authorizedLogin={authorizedLogin}
                setAuthorizedLogin={setAuthorizedLogin}
                navigate={navigate}
                setTitle = {setTitle}
              />
            }
          />
          <Route exact path="/register" element={<Register setTitle={setTitle} navigate={navigate}/>} />
          <Route
            exact
            path="/dashboard"
            element={
              <Dashboard
                authorizedLogin={authorizedLogin}
                setAuthorizedLogin={setAuthorizedLogin}
                logoutHandler={logoutHandler}
                navigate={navigate}
              />
            }
          />
          <Route
            exact
            path="/invalidCredentials"
            element={
              <RedirectWithMessage
                properties={{
                  title: title,
                  message: "Please re-login",
                  redirectionPageName: "login",
                  primaryLink: "/login",
                  color: "red",
                }}
                navigate={navigate}
              />
            }
          />
          <Route
            exact
            path="/sessionExpired"
            element={
              <RedirectWithMessage
                properties={{
                  title: "Session Expired",
                  message: "Please re-login",
                  redirectionPageName: "login",
                  primaryLink: "/login",
                  color: "red",
                }}
                navigate={navigate}
              />
            }
          />
          <Route
            exact
            path="/logoutSuccess"
            element={
              <RedirectWithMessage
                properties={{
                  title: "Logout Success",
                  message: "Please login",
                  redirectionPageName: "login",
                  primaryLink: "/login",
                  color: "green",
                }}
                navigate={navigate}
              />
            }
          />
          <Route
            exact
            path="/registrationSuccess"
            element={
              <RedirectWithMessage
                properties={{
                  title: "Registration Success",
                  message: "Please login",
                  redirectionPageName: "login",
                  primaryLink: "/login",
                  color: "green",
                }}
                navigate={navigate}
              />
            }
          />
          <Route
            exact
            path="/registrationFailure"
            element={
              <RedirectWithMessage
                properties={{
                  title: "Registration Failed",
                  message: title + ". Please register",
                  redirectionPageName: "Registration",
                  primaryLink: "/register",
                  color: "red",
                }}
                navigate={navigate}
              />
            }
          />
          <Route exact path="/" element={<Home />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
