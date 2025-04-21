"use client";
import * as React from "react";
import { useState } from "react";
import { ModalHeader } from "./modalHeader";
import { LoginForm } from "./loginForm";
import { Divider } from "./divider";
import { GoogleLoginButton } from "./googleLoginButton";
import { SignUpPrompt } from "./signUpPrompt";

export default function LoginModal() {
  const [isOpen, setIsOpen] = useState(true);

  const closeDialog = () => {
    setIsOpen(false);
  };

  if (!isOpen) return null;

  return (
    <div className="flex fixed inset-0 justify-center items-center bg-black bg-opacity-50 z-[1000]">
      <dialog
        open
        className="relative p-8 rounded-lg bg-[white] max-w-[400px] w-[90%]"
      >
        <ModalHeader onClose={closeDialog} />
        <LoginForm />
        <Divider text="또는" />
        <GoogleLoginButton />
        <SignUpPrompt />
      </dialog>
    </div>
  );
}
