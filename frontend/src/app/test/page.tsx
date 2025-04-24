"use client";
import { useAuth } from "@/contexts/AuthContext";

 // 클라이언트 컴포넌트 선언


export default function Dashboard() {
  const { isLoggedIn, login, logout } = useAuth();

  return (
    <div>
      <h1>대시보드</h1>
      {isLoggedIn ? (
        <>
          <p>로그인 상태입니다! 🎉</p>
          <button onClick={logout}>로그아웃</button>
        </>
      ) : (
        <>
          <p>로그인이 필요합니다.</p>
          <button onClick={login}>로그인</button>
        </>
      )}
    </div>
  );
}