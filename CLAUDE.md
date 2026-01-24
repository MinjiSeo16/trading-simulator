# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Development Rules

- 항상 코드 수정 전에 Plan을 먼저 제시한다
- 사용자가 OK라고 답한 후에만 코드를 수정한다
- 한 번에 하나의 기능만 구현한다
- Spring Boot에서는 Controller / Service / Repository 계층을 분리한다
- DTO와 Entity를 분리한다

## Project Overview

Trading Simulator is a Spring Boot 3.x web application using Java 21. It simulates stock trading with real-time price data.

### Key Features
- **Trading**: Market orders only, integer share quantities
- **Authentication**: JWT access token
- **Persistence**: MySQL (users, wallet, assets, trades)
- **Real-time data**: Redis (prices, recent price history)

## Build Commands

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.minjiseo.tradingsimulator.TradingSimulatorApplicationTests"

# Run a single test method
./gradlew test --tests "com.minjiseo.tradingsimulator.TradingSimulatorApplicationTests.contextLoads"

# Clean build
./gradlew clean build
```

## Architecture

- **Main package**: `com.minjiseo.tradingsimulator`
- **Entry point**: `TradingSimulatorApplication.java`
- **Framework**: Spring Boot with Spring Web (REST support)
- **Database**: MySQL + Spring Data JPA
- **Cache**: Redis + Spring Data Redis
- **Testing**: JUnit 5 via Spring Boot Test

## Project Structure

```
src/
├── main/
│   ├── java/com/minjiseo/tradingsimulator/   # Application code
│   └── resources/
│       └── application.yml                    # Configuration
└── test/
    └── java/com/minjiseo/tradingsimulator/   # Test code
```
