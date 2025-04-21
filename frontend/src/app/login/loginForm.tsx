"use client";
import * as React from "react";
import { useState } from "react";

export function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);

  const toggleRememberMe = () => {
    setRememberMe(!rememberMe);
  };

  return (
    <form className="mb-6" onSubmit={(e) => e.preventDefault()}>
      <div className="mb-4">
        <label className="mb-2 text-sm text-zinc-800" htmlFor="email">
          이메일
        </label>
        <input
          id="email"
          type="email"
          placeholder="이메일을 입력하세요"
          className="px-3 py-2 w-full text-sm rounded border border-solid border-zinc-300"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div className="mb-4">
        <label className="mb-2 text-sm text-zinc-800" htmlFor="password">
          비밀번호
        </label>
        <input
          id="password"
          type="password"
          placeholder="비밀번호를 입력하세요"
          className="px-3 py-2 w-full text-sm rounded border border-solid border-zinc-300"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <div className="flex justify-between items-center mb-6">
        <label className="flex gap-2 items-center text-sm cursor-pointer text-stone-500">
          <input
            type="checkbox"
            checked={rememberMe}
            onChange={toggleRememberMe}
          />
          <span>로그인 상태 유지</span>
        </label>
        <a href="#" className="text-sm text-cyan-700 no-underline">
          비밀번호 찾기
        </a>
      </div>
      <button className="p-3 mb-4 w-full text-sm font-semibold bg-cyan-700 rounded cursor-pointer border-[none] text-[white]">
        로그인
      </button>
    </form>
  );
}
