/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    loader: "akamai",
    path: "/",
  },
  env: {
    BACK_EC2: process.env.BACK_EC2,
  },
  api_domain: "http://localhost:8080",
};

const withImages = require("next-images");

(module.exports = nextConfig), withImages;
