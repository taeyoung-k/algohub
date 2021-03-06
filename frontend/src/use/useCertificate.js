import { useState, useEffect } from "react";
import axios from "axios";

function useCertificate(prop) {
  const [certificate, setCertificate] = useState({
    loading: false,
    localUserName: null,
    localUserId: null,
  });
  function init() {
    return axios
      .get("/api/auth/me")
      .then((res) =>
        setCertificate({
          loading: true,
          localUserName: res.data.data.m_name,
          localUserId: res.data.data.m_id,
        })
      )
      .catch((err) => {
        console.log("login time is ended");
        if (!prop) {
          console.log("실행!");
          window.location.href = "/auth/login";
        }
      });
  }

  useEffect(() => {
    init();
  }, []);

  return certificate;
}

export default useCertificate;
