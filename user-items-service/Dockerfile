# Build & Dev Stage
FROM gradle:8.5-jdk17 AS dev
WORKDIR /app

# Copy Gradle files for dependency caching
COPY build.gradle settings.gradle gradlew* ./
COPY gradle ./gradle

# Pre-fetch dependencies
RUN ./gradlew dependencies --no-daemon || return 0

# Copy source code
COPY . .

# Expose Spring Boot port
EXPOSE 8080

# Run the app in development mode with hot reload
CMD ["./gradlew", "bootRun", "--no-daemon"]
