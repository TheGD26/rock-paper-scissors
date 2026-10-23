# Rock Paper Scissors

A Rock Paper Scissors game with a Spring Boot 3 (Java 17) REST API and a Vue 3 frontend.

## Running locally

```bash
mvn spring-boot:run
```

Open http://localhost:8080. The API is under `/api/games`.

Run the tests with `mvn test`.

## Deployment

The Java backend (which also serves the frontend) runs on **Render** using the `Dockerfile`.
If you want, you can also serve the static frontend from **Netlify**, which forwards `/api` calls to Render.

### Render (backend + frontend)

1. In the Render dashboard, click **New → Web Service**.
2. Connect this GitHub repository.
3. Render detects the `Dockerfile` and picks the **Docker** runtime.
4. Choose the **Free** plan.
5. Click **Deploy Web Service** and wait for the build to finish.
6. Open `https://<name>.onrender.com`, where `<name>` is your service name.

You don't need to set a port. The container reads the `PORT` variable that Render provides.

### Netlify (optional, frontend only)

1. In Netlify, click **Add new site → Import an existing project** and choose this repository.
2. Leave the **build command** empty.
3. Check that the **publish directory** is `src/main/resources/static`. Netlify reads it from `netlify.toml`.
4. In `netlify.toml`, replace `RENDER_APP_NAME` with your Render service name (for example, `https://my-rps.onrender.com/api/:splat`), then commit and push.
5. Deploy. The frontend calls relative `/api/games` URLs, and Netlify forwards them to Render, so you don't need to set up CORS.

### Notes

- Render's free plan puts the service to sleep after about 15 minutes without traffic. The first request after that can take 30 to 60 seconds while it wakes up.
- Games are stored in memory, so they are lost whenever the service restarts or goes to sleep.
