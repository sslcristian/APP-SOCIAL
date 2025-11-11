const express = require('express');
const router = express.Router();
const pool = require('../db');
const multer = require('multer');
const upload = multer({ dest: 'uploads/' });
router.get('/', async (req, res) => {
  try {
    const posts = await pool.query(`
      SELECT p.*, u.nombre AS usuario_nombre, u.foto_perfil AS usuario_foto
      FROM posts p JOIN usuarios u ON p.usuario_id = u.id
      ORDER BY p.fecha DESC
    `);
    res.json(posts.rows);
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});
router.post('/', upload.single('imagen'), async (req, res) => {
  try {
    const { usuario_id, texto } = req.body;
    let imagen_url = null;
    if (req.file) {
      imagen_url = req.protocol + '://' + req.get('host') + '/uploads/' + req.file.filename;
    }
    const post = await pool.query('INSERT INTO posts (usuario_id, texto, imagen_url) VALUES ($1, $2, $3) RETURNING *', [usuario_id, texto, imagen_url]);
    res.json(post.rows[0]);
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});
module.exports = router;
