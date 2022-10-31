import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const Dashboard = (props) => {
  const navigate = useNavigate();
  
  useEffect(() => {
    if (props.authorizedLogin === "") {
      navigate("/sessionExpired");
    }
  });

  return (
    <div>
      <div className="container my-3">
        <h2>Dashboard</h2>
        <p>Welcome {props.authorizedLogin}</p>
        <button className="btn btn-danger" onClick={props.logoutHandler}>
          Log out
        </button>
      </div>
    </div>
  );
};
