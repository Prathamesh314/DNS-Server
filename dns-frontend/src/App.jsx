/* eslint-disable no-unused-vars */
import "./App.css";
import { useState } from "react";
import axios from "axios";
function App() {
  const requestOptions = {
    method: "GET",
    mode: "no-cors",
  };
  const [ipAddress, setIpAddress] = useState("");
  const [name, setName] = useState("");

  // console.log(name);
  const handleOnClick = async () => {
    console.log(name);
    const data = await axios
      .get(`http://localhost:9191/nameserver/${name}`, requestOptions)
      .then((data) => setIpAddress(data.data.ip));
    // console.log(data);
  };

  return (
    <div className="container">
      <div className="title">DNS-SERVER</div>
      <div className="row">
        <div className="col-md-12 col-sm-12 col-xs-12">
          <section className="main-timeline-section">
            <div className="timeline-start"></div>
            <div className="conference-center-line"></div>
            <div className="conference-timeline-content">
              <div className="timeline-article timeline-article-top">
                <div className="content-date">
                  <span>Authorative Nameservices</span>
                </div>
                <div className="meta-date"></div>
                <div className="content-box">
                  <p>www.google.com</p>
                </div>
              </div>

              <div className="timeline-article timeline-article-bottom">
                <div className="content-date">
                  <span>Top Level Domain Nameservcies</span>
                </div>
                <div className="meta-date"></div>
                <div className="content-box">
                  <p>.com .in .org .gov</p>
                </div>
              </div>

              <div className="timeline-article timeline-article-top">
                <div className="content-date">
                  <span>Root Nameservices</span>
                </div>
                <div className="meta-date"></div>
                <div className="content-box">
                  <p>Check for .</p>
                </div>
              </div>

              <div className="timeline-article timeline-article-bottom">
                <div className="content-date">
                  <span>DNS-Resolver</span>
                </div>
                <div className="meta-date"></div>
                <div className="content-box">
                  <p>Gives The IP</p>
                </div>
              </div>
            </div>
            <div className="timeline-end"></div>
          </section>
        </div>
      </div>
      <div className="text">
        <div className="form__group field">
          <input
            type="input"
            className="form__field"
            placeholder="Name"
            name="name"
            onChange={(e) => setName(e.target.value)}
            id="name"
            required
          />
          <label htmlFor="name" className="form__label">
            Name
          </label>
        </div>
      </div>
      <div className="button">
        <button className="fill" onClick={handleOnClick}>
          Submit
        </button>
      </div>
      <div className="ans">
        <div className="output">IP ADD: {ipAddress}</div>
      </div>
    </div>
  );
}

export default App;
