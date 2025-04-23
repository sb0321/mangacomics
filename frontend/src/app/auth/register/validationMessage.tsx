import * as React from "react";

interface ValidationMessageProps {
  message: string;
}

export function ValidationMessage({ message }: ValidationMessageProps) {
  return <span className="text-sm text-[red]">{message}</span>;
}
