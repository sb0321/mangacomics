export const apiUrl = process.env.NEXT_PUBLIC_API_URL;

export async function post<T = any>(endpoint: string, data: any): Promise<T> {
  try {
    const res = await fetch(`${apiUrl}${endpoint}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });

    if (!res.ok) {
      // 서버에서 에러 메시지를 반환하는 경우
      const errorData = await res.json().catch(() => ({}));
      throw new Error(errorData.message || "API 요청 실패");
    }
    return res.json();
  } catch (error: any) {
    // 네트워크 에러 등
    throw new Error(error.message || "네트워크 오류");
  }
}