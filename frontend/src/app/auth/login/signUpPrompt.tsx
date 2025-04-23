import * as React from "react";

export function SignUpPrompt() {
  return (
    <p className="text-sm text-center text-stone-500">
      <span>계정이 없으신가요? </span>
      <a href="#" className="text-cyan-700 no-underline">
        회원가입
      </a>
    </p>
  );
}
