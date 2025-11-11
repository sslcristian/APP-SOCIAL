Social Backend - Ready for Render
---------------------------------
1. Set environment variables in Render: DATABASE_URL (your Neon URL), JWT_SECRET.
2. Deploy the repo in Render (render.yaml present).
3. After deploy, open / to test.
Endpoints:
- POST /api/auth/register { nombre, email, password }
- POST /api/auth/login { email, password }
- GET  /api/posts
- POST /api/posts (multipart form-data usuario_id,texto,imagen)
- GET  /api/comments/:postId
- POST /api/comments { post_id, usuario_id, texto }
