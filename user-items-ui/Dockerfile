# Use Node.js for development environment
FROM node:18-alpine

# Set working directory inside the container
WORKDIR /app

# Copy only package files first (to leverage Docker layer caching)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire frontend code
COPY . .

# Expose React development server port
EXPOSE 3000

# Start React app in development mode (hot reload enabled)
CMD ["npm", "start"]
