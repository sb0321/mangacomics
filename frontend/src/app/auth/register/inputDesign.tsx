"use client";
import { post } from "@/utils/Api";
import * as React from "react";
import { useState } from "react";
import { RegisterFormInput } from "./registerFormInput";

interface FormData {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

interface FormErrors {
  [key: string]: string;
}

function InputDesign() {
  const [formData, setFormData] = useState<FormData>({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState<FormErrors>({});

  function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    // Validate all fields before submission
    validateField("username");
    validateField("email");
    validateField("password");
    validateField("confirmPassword");

    if (
        errors.username ||
        errors.email ||
        errors.password ||
        errors.confirmPassword
    ) {
        return;
    }

    try {
        post("/api/v1/auth/register", {
            username: formData.username,
            email: formData.email,
            password: formData.password
        });

        window.location.href = "/";
    } catch (err: any) {
        console.error(err);
        alert("회원가입에 실패했습니다. 다시 시도해주세요.");
    }
  }

  function updateField(field: keyof FormData, value: string) {
    setFormData((prev) => ({
      ...prev,
      [field]: value,
    }));
    validateField(field);
  }

  function validateField(field: keyof FormData) {
    const newErrors: FormErrors = { ...errors };

    switch (field) {
      case "username":
        if (!formData.username) {
          newErrors.username = "아이디를 입력해주세요";
        } else {
          delete newErrors.username;
        }
        break;
      case "email":
        if (!formData.email.includes("@")) {
          newErrors.email = "유효한 이메일을 입력해주세요";
        } else {
          delete newErrors.email;
        }
        break;
      case "password":
        if (formData.password.length < 8) {
          newErrors.password = "비밀번호는 8자 이상이어야 합니다";
        } else {
          delete newErrors.password;
        }
        break;
      case "confirmPassword":
        if (formData.password !== formData.confirmPassword) {
          newErrors.confirmPassword = "비밀번호가 일치하지 않습니다";
        } else {
          delete newErrors.confirmPassword;
        }
        break;
    }

    setErrors(newErrors);
  }

  return (
    <main className="px-5 py-0 mx-auto mt-32 mb-0 max-w-[1240px] max-sm:mx-auto max-sm:mt-24 max-sm:mb-0">
      <h1 className="mb-8 text-3xl font-semibold text-center">회원가입</h1>
      <form
        className="p-8 mx-auto my-0 rounded-lg bg-[white] max-w-[480px] shadow-[0_2px_8px_rgba(0,0,0,0.1)]"
        onSubmit={handleSubmit}
      >
        <RegisterFormInput
          label="닉네임"
          type="text"
          value={formData.username}
          onChange={(value) => updateField("username", value)}
          error={errors.username}
        />
        <RegisterFormInput
          label="이메일"
          type="email"
          value={formData.email}
          onChange={(value) => updateField("email", value)}
          error={errors.email}
        />
        <RegisterFormInput
          label="비밀번호"
          type="password"
          value={formData.password}
          onChange={(value) => updateField("password", value)}
          error={errors.password}
        />
        <RegisterFormInput
          label="비밀번호 확인"
          type="password"
          value={formData.confirmPassword}
          onChange={(value) => updateField("confirmPassword", value)}
          error={errors.confirmPassword}
        />
        <div className="mb-8" />
        <button
          className="p-3 w-full text-base font-semibold bg-cyan-700 rounded cursor-pointer border-[none] text-[white]"
          type="submit"
        >
          가입하기
        </button>
      </form>
    </main>
  );
}

export default InputDesign;
