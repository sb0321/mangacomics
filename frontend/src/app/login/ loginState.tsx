"use client";
import * as React from "react";
import { LoadingSpinner } from "./LoadingSpinner";

export const LoadingState: React.FC = () => {
  return (
    <section
      className="w-full bg-[#efefef] text-[#838383] rounded-md flex flex-col items-center justify-center p-6 min-h-[100px] text-lg my-2 font-['Inter']"
      role="status"
      aria-label="Content is loading"
    >
      <p>Generating content...</p>
      <div className="mt-5">
        <LoadingSpinner />
      </div>
    </section>
  );
};

export default LoadingState;
