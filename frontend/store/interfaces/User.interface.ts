import { AxiosError } from "axios";

export interface UserParams {
  nickname: string;
  email: string;
  count: number;
  data: string;
  error: AxiosError | null;
}
export interface User {
  isLoading: boolean;
  users: User[];
  userObj: [];
  error: AxiosError | null;
}
