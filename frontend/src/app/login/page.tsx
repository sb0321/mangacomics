"use client";

import { useState } from "react";
import { post } from "@/utils/api";
import { useAuth } from "@/contexts/AuthContext";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { login } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    try {
      await post("/api/v1/login", { email, password });
      login();
      window.location.href = "/";
    } catch (err: any) {
      setError(err.message || "이메일 또는 비밀번호가 올바르지 않습니다.");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-yellow-100 via-orange-100 to-yellow-200 bg-cover bg-center" style={{ backgroundImage: "url('https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=1500&q=80')" }}>
      <div className="bg-white/90 rounded-2xl shadow-2xl p-10 max-w-md w-full border border-yellow-200">
        <h2 className="text-3xl font-serif font-bold text-yellow-800 text-center mb-8 tracking-wide">
          📚 소설 로그인
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-6">
            <label className="block mb-2 text-yellow-900 font-semibold">이메일</label>
            <input
              type="email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
              className="w-full px-4 py-2 rounded-md border border-yellow-200 bg-yellow-50 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
            />
          </div>
          <div className="mb-6">
            <label className="block mb-2 text-yellow-900 font-semibold">비밀번호</label>
            <input
              type="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              required
              className="w-full px-4 py-2 rounded-md border border-yellow-200 bg-yellow-50 focus:outline-none focus:ring-2 focus:ring-yellow-400 transition"
            />
          </div>
          {error && (
            <div className="text-red-600 text-center mb-4">{error}</div>
          )}
          <button
            type="submit"
            className="w-full py-3 bg-gradient-to-r from-yellow-400 to-yellow-600 text-white font-bold rounded-lg shadow-md hover:from-yellow-500 hover:to-yellow-700 transition"
          >
            로그인
          </button>
        </form>
        <div className="mt-8 text-center text-yellow-700 italic text-sm">
          소설 플랫폼 망가코믹스
        </div>
      </div>
    </div>
  );
}