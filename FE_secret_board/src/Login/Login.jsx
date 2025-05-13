import React from "react";
import { Button } from "react-bootstrap";

function Login() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6"> {/* 너비 조절 */}

          <h1 className="text-center">Login</h1>

          <form>
            <div className="mb-3">
              <label>Id:</label>
              <input type="text" name="Id" placeholder="Id" className="form-control" />
            </div>

            <div className="mb-3">
              <label>Password:</label>
              <input type="password" name="password" placeholder="Password" className="form-control" />
            </div>

            <div className="d-grid gap-2">
              <Button variant="primary" size="lg" type="submit">Login</Button>
              <Button variant="primary" size="lg" type="button">SignUp</Button>
            </div>
          </form>

        </div>
      </div>
    </div>
  );
}
export default Login;
