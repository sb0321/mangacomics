"use client";
import * as React from "react";
import { useState } from "react";
import { post } from "@/utils/Api";

export function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    setError("");
    try {
      await post("/api/v1/auth/login", { email, password });
      window.location.href = "/";
    } catch (err: any) {
      setError(err.message || "서버와 통신할 수 없습니다.");
    }
  };

  return (
    <form className="mb-6" onSubmit={handleSubmit}>
      <div className="mb-4">
        <label className="mb-2 text-sm text-zinc-800">이메일</label>
        <input
          type="email"
          placeholder="이메일을 입력하세요"
          className="px-3 py-2 w-full text-sm rounded border border-solid border-zinc-300"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
      </div>

      <div className="mb-4">
        <label className="mb-2 text-sm text-zinc-800">비밀번호</label>
        <input
          type="password"
          placeholder="비밀번호를 입력하세요"
          className="px-3 py-2 w-full text-sm rounded border border-solid border-zinc-300"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </div>
      {error && (
        <div className="mb-2 text-red-600 text-sm text-center">{error}</div>
      )}
      <button
        type="submit"
        className="p-3 mb-4 w-full text-sm font-semibold bg-cyan-700 rounded cursor-pointer border-[none] text-[white]"
      >
        로그인
      </button>
    </form>
  );
}
