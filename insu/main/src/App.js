import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./Routes/main/Main";
import Login from "./Routes/login/Login";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={`${process.env.PUBLIC_URL}/`} element={<Main />} />
        <Route path={`${process.env.PUBLIC_URL}/login`} element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
