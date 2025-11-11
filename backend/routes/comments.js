const express = require('express');
const router = express.Router();
const pool = require('../db');
router.get('/:postId', async (req, res) => {
  try {
    const comments = await pool.query(
      `SELECT c.*, u.nombre AS usuario_nombre FROM comentarios c JOIN usuarios u ON c.usuario_id = u.id WHERE c.post_id = $1 ORDER BY c.fecha ASC`,
      [req.params.postId]
    );
    res.json(comments.rows);
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});
router.post('/', async (req, res) => {
  try {
    const { post_id, usuario_id, texto } = req.body;
    const comment = await pool.query('INSERT INTO comentarios (post_id, usuario_id, texto) VALUES ($1, $2, $3) RETURNING *', [post_id, usuario_id, texto]);
    res.json(comment.rows[0]);
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});
module.exports = router;
