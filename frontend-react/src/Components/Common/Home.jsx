import React from "react";
import { Link } from "react-router-dom";

export const Home = () => {
  return (
    <div>
      <h2>Welcome to Employee Management System</h2>
      <div>
        <Link to="/login" className="m-3">
          <button className="btn btn-primary">Login</button>
        </Link>
        <Link to="/register" className="m-3">
          <button className="btn btn-primary">Register</button>
        </Link>
      </div>
    </div>
  );
};
