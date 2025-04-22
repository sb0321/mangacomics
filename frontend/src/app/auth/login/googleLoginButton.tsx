import * as React from "react";

export function GoogleLoginButton() {
  return (
    <button className="flex gap-2 justify-center items-center p-3 w-full text-sm rounded border border-solid cursor-pointer bg-[white] border-zinc-300">
      <span>Google로 로그인</span>
    </button>
  );
}
