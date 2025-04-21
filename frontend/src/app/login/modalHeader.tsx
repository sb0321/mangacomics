"use client";
import * as React from "react";

interface ModalHeaderProps {
  onClose: () => void;
}

export function ModalHeader({ onClose }: ModalHeaderProps) {
  return (
    <header className="mb-6 text-center">
      <button
        className="absolute top-4 right-4 p-1 cursor-pointer border-[none]"
        onClick={onClose}
        aria-label="Close modal"
      >
        ✕
      </button>
      <h2 className="mb-2 text-xl font-semibold">환영합니다</h2>
      <p className="text-sm text-stone-500" />
    </header>
  );
}
