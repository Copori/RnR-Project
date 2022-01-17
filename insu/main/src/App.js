import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./Routes/main/Main";
import Login from "./Routes/login/Login";
import BookDetail from "./Routes/bookDetail/BookDetail";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={`${process.env.PUBLIC_URL}/`} element={<Main />} />
        <Route path={`${process.env.PUBLIC_URL}/login`} element={<Login />} />
        <Route
          path={`${process.env.PUBLIC_URL}/detail`}
          element={<BookDetail />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
