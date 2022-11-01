import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export const RedirectWithMessage = (props) => {
  const [time, setTime] = useState(5);

  useEffect(() => {
    //changes time every second from 5 to 1
    const interval = setInterval(() => setTime((prev) => prev - 1), 1000);

    setTimeout(() => {
      clearInterval(interval);
      props.navigate(props.properties.primaryLink);
    }, 5100);

    return () => clearInterval(interval);
  });

  return (
    <div className="container my-2">
      <h2>{props.properties.title}</h2>
      <p style={{ color: props.properties.color }}>
        {props.properties.message} <Link to={props.properties.primaryLink}>here</Link>
      </p>
      <p>
        This page will automatically redirect to {props.properties.redirectionPageName} page in {time} secs.
      </p>
    </div>
  );
};
