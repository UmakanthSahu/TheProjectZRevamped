import React from "react";

export const Dashboard = (props) => {
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
