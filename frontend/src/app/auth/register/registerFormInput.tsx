"use client";

interface FormInputProps {
  label: string;
  type: string;
  value: string;
  onChange: (value: string) => void;
  error?: string;
}

export function RegisterFormInput({
  label,
  type,
  value,
  onChange,
  error,
}: FormInputProps) {
  return (
    <div className="mb-5">
      <label className="mb-2 font-medium">
        <span>{label}</span>
      </label>
      <input
        type={type}
        className="p-2.5 w-full text-base rounded border border-solid border-zinc-300"
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      {error && <span className="text-sm text-[red]">{error}</span>}
    </div>
  );
}
