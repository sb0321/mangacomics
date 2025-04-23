import * as React from "react";

interface DividerProps {
  text: string;
}

export function Divider({ text }: DividerProps) {
  return (
    <div className="relative mb-4 text-center">
      <span className="relative px-3 py-0 text-sm bg-[white] text-stone-500 z-[1]">
        {text}
      </span>
      <div className="absolute inset-x-0 top-2/4 z-0 h-px bg-zinc-300" />
    </div>
  );
}
