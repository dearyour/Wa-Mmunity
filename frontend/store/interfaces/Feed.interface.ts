import { AxiosError } from "axios";

export interface FeedParams {
  feedId: number;
  challengeId: number;
  image: string;
  content: string;
  userId: number;
  createdDate: Date;
  modifiedDate: Date;
  feedlike: Array<number>;
}

export interface Feed {
  items: any;
  itemss: any;
  isLoading: boolean;
  error: AxiosError | null;
}

export interface FeedParamType {
  feedId: number;
}
