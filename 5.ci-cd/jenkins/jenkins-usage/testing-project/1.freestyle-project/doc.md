#### Freestyle project: The traditional Jenkins job type with a GUI-based configuration.
---
- Create Project
  - Click "New Item" on Jenkins dashboard
  - Enter name: my-first-freestyle
  - Select "Freestyle project"
  - Click OK
- Configure Source Code Management
  - Scroll to `Source Code Management`
  - Select `Git`
  - Repository URL : `https://github.com/jenkins-docs/simple-java-maven-app.git`
  - (This is a public test repo, no credentials needed)
- Add build steps:
  - Scroll to `Build Steps`
  - Click `Add build step` -> `Execute shell`
  - Enter
    ```bash
    echo "Building project..."
    echo "Current directory: $(pwd)"
    ls -la
    echo "Build completed successfully!"
    ```
- Add post-build Action:
  - Scroll to `Post-build Action`
  - Click `Add post-build action` -> `Archive the artifacts`
  - Files to archive: `/*.txt` (Archives any txt file)
- Save and Build
  - Click `Save`
  - Click `Build Now`
  - Click on the build number (e.g., #1) to see console output
 > What you'll learn: Basic job creation, SCM integration, build steps, and viewing results.
 