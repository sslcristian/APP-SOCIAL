SOCIAL APP - Full package (Backend + Android)
============================================
Contents:
- backend/      -> Node.js + Express backend, ready for Render
- render.yaml   -> Render deployment config
- android_app/  -> Android Studio project (package: nose, app name: APP social moviles)

Quick deploy steps (backend):
1. Create GitHub repo and push entire folder contents (root of repo should contain render.yaml and backend/).
2. In Render, create New Web Service -> connect GitHub repo. Render will use render.yaml.
3. In Render service settings, add env vars:
   - DATABASE_URL = your Neon connection string (include ?sslmode=require)
   - JWT_SECRET = a secret string
4. Deploy and get the public URL (e.g. https://social-backend.onrender.com/)

Android setup:
1. Open android_app in Android Studio.
2. In nose/ApiClient.java change BASE_URL to your Render URL (must end with /)
3. Build and run on emulator/device.

Notes:
- Run the SQL in backend/sql/schema.sql on your Neon DB before using the app.
- This project is a starter; add production hardening, validation, and security as needed.
