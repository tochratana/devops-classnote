## DevOps Exam Review Cheat Sheet

### 1. Nexus (Repository Manager)

What is Nexus?: Tool use to store artifacts, package, images.

Example:
- .jar
- .war
- .npm
- docker images
- Maven dependency

Why use Nexus?
- Central storage (ទទួលបាននៅ storage មួយដែលមាននៅ privacy, private ដែលមិនមានអ្នកណាអាចប្រើប្រាស់បាន)
- Version control for build
- CI/CD can push & pull artifact

```bash
Developer push code → Jenkins build → push artifact to Nexus → deploy from Nexus
```


### 2. Jenkins Pipeline (VERY IMPORTANT)