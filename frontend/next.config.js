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
  api_domain: "https://j6a101.p.ssafy.io",

  distDir: "dist",

  // exportPathMap: { page: "/" },
};

const withImages = require("next-images");

(module.exports = nextConfig), withImages;
