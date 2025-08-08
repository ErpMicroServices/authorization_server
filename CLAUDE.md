# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this module.

## Module Overview

The `authorization_server` module is a Spring Boot OAuth2 Authorization Server that provides centralized authentication and authorization services for the ERP microservices ecosystem. It implements OAuth2 and OpenID Connect protocols to secure API access across all microservices.

## Technology Stack

- **Framework**: Spring Boot 3.3.4
- **Java Version**: Java 21
- **Build Tool**: Gradle 
- **Security**: Spring Security OAuth2 Authorization Server
- **Database**: PostgreSQL (via JPA/Hibernate)
- **Architecture**: Microservice
- **Container**: Docker support with Spring Boot build images

## Project Structure

```
authorization_server/
├── build.gradle                 # Gradle build configuration
├── settings.gradle              # Gradle settings
├── gradlew                      # Gradle wrapper (Unix)
├── gradlew.bat                 # Gradle wrapper (Windows)
├── docker-compose.yml          # Local development setup
├── gradle/wrapper/             # Gradle wrapper files
└── src/
    ├── main/
    │   ├── java/org/erpmicroservices/authorization_server/
    │   │   ├── AuthorizationServerApplication.java
    │   │   ├── SecurityConfig.java
    │   │   ├── models/          # JPA entities
    │   │   ├── repositories/    # Data access layer
    │   │   └── services/        # Business logic layer
    │   └── resources/
    │       └── application.yml  # Configuration
    └── test/
        └── java/                # Test classes
```

## Build and Development Commands

### Gradle Commands
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run tests
./gradlew test

# Build Docker image
./gradlew bootBuildImage

# Clean build artifacts
./gradlew clean

# Generate dependency report
./gradlew dependencies
```

### Docker Operations
```bash
# Start local development environment
docker-compose up

# Build and tag Docker image
./gradlew bootBuildImage
```

## Configuration

### Application Configuration (`application.yml`)
- **Server Configuration**: Port, SSL settings
- **Database Connection**: PostgreSQL connection details
- **OAuth2 Settings**: Client registrations, token settings
- **Security Configuration**: CORS, CSRF, security headers
- **Logging Configuration**: Log levels and patterns

### Key Configuration Areas
- **OAuth2 Authorization Server**: Client management, token configuration
- **JPA Configuration**: Database connection and Hibernate settings
- **Security Configuration**: Authentication providers and security rules
- **Actuator Configuration**: Health checks and monitoring endpoints

## OAuth2 Implementation

### Core Components
- **AuthorizationServerApplication**: Main Spring Boot application
- **SecurityConfig**: Security configuration and bean definitions
- **JpaOAuth2AuthorizationService**: Custom authorization storage
- **JpaRegisteredClientRepository**: Client application management
- **JpaUserDetailsService**: User authentication service

### OAuth2 Entities
- **Client**: Registered client applications
- **User**: System users with authentication credentials
- **Authority**: User roles and permissions
- **Authorization**: Active authorization grants
- **AuthorizationConsent**: User consent records

## Development Workflow

### Adding New OAuth2 Clients
1. **Database Registration**: Add client to oauth2_registered_client table
2. **Configuration**: Update application.yml if needed
3. **Testing**: Create integration tests for new client flows
4. **Documentation**: Update client integration guides

### Security Configuration Changes
1. **SecurityConfig Updates**: Modify Spring Security configuration
2. **Testing**: Ensure existing flows still work
3. **Authorization Rules**: Update endpoint security rules
4. **Integration Testing**: Test with dependent microservices

### User Management
1. **User Entity**: Modify User model for new requirements
2. **UserDetailsService**: Update authentication logic
3. **Authority Management**: Add new roles/permissions
4. **Migration Scripts**: Database updates for user schema

## Testing Standards

### Unit Testing
- **Service Layer**: Test OAuth2 services and custom logic
- **Security Configuration**: Test security rules and configurations
- **Repository Layer**: Test data access patterns
- **Model Validation**: Test entity validation rules

### Integration Testing
- **OAuth2 Flows**: Test complete authorization flows
- **Database Integration**: Test JPA repositories and services
- **Security Integration**: Test endpoint security
- **Client Authentication**: Test various client authentication methods

### Testing Best Practices
- **Test Containers**: Use PostgreSQL test containers for integration tests
- **Security Testing**: Test both successful and failed authentication
- **Token Testing**: Test token generation, validation, and expiration
- **Mock External Dependencies**: Mock other microservices during testing

## Security Considerations

### OAuth2 Security
- **PKCE**: Proof Key for Code Exchange implementation
- **Client Authentication**: Multiple client authentication methods
- **Scope Management**: Fine-grained permission control
- **Token Security**: Secure token generation and storage

### Application Security
- **Password Security**: Bcrypt password encoding
- **Session Management**: Secure session handling
- **CORS Configuration**: Proper cross-origin configuration
- **HTTPS Enforcement**: SSL/TLS configuration

## Dependencies

### Core Dependencies
- **Spring Boot Starter OAuth2 Authorization Server**: OAuth2 implementation
- **Spring Boot Starter Data JPA**: Database access
- **Spring Boot Starter Actuator**: Monitoring and management
- **PostgreSQL Driver**: Database connectivity
- **Lombok**: Code generation for boilerplate reduction

### Development Dependencies
- **Spring Boot DevTools**: Development utilities
- **Spring Boot Docker Compose**: Local development support
- **Spring Boot Starter Test**: Testing framework

## Docker Integration

### Build Image Configuration
- **Image Name**: `erpmicroservices/authorization_server`
- **Tags**: Latest and version-specific tags
- **Environment**: Docker profile support
- **Spring Profiles**: Docker-specific configuration activation

### Container Requirements
- **Database**: Requires PostgreSQL database connection
- **Port**: Exposes OAuth2 endpoints on configured port
- **Health Checks**: Actuator health endpoints available
- **Environment Variables**: Supports externalized configuration

## Integration Points

### Microservice Integration
- **Resource Servers**: Other microservices validate tokens with this server
- **Client Applications**: Web and mobile apps authenticate through this server
- **API Gateway**: Integration with API gateway for token validation
- **Service Discovery**: Registration with service discovery mechanisms

### Database Integration
- **Authorization Database**: Stores OAuth2 tokens and client data
- **User Database**: May integrate with existing user management systems
- **Audit Logging**: Security event logging and monitoring

## Performance Considerations

### Token Management
- **Token Storage**: Efficient database storage and retrieval
- **Token Validation**: Fast token validation for high-throughput scenarios  
- **Cache Strategy**: Consider caching for frequently accessed data
- **Connection Pooling**: Database connection optimization

### Scalability
- **Horizontal Scaling**: Support for multiple authorization server instances
- **Load Balancing**: Stateless design for load balancer compatibility
- **Database Clustering**: Support for database high availability
- **Health Monitoring**: Comprehensive health check endpoints

## Important Notes

- **Java 21**: Uses modern Java features and performance improvements
- **Spring Boot 3.3.4**: Latest Spring Boot with enhanced OAuth2 support
- **Production Ready**: Includes actuator, security, and monitoring features
- **Microservice Architecture**: Designed for distributed system integration
- **Standards Compliance**: Implements OAuth2 and OpenID Connect specifications
- **Security First**: Comprehensive security configuration and best practices