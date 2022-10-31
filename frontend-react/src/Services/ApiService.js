const BASE_API_URL = "http://localhost:8080/api/v1";
export const LOGIN_URL = BASE_API_URL +"/loginEmployee";
export const REGISTER_URL = BASE_API_URL + "/registerEmployee";


export const getJSONRequestData = (data) => { return ({
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify(data),
});
}
