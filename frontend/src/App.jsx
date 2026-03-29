import { useState, useEffect } from 'react'
import './App.css'

const API_URL = 'http://localhost:8080'

const initialForm = { nome: '', sobrenome: '', email: '', celular: '', cep: '' }

function App() {
  const [form, setForm] = useState(initialForm)
  const [clientes, setClientes] = useState([])
  const [errors, setErrors] = useState({})
  const [mensagem, setMensagem] = useState(null)
  const [editingId, setEditingId] = useState(null)

  useEffect(() => {
    carregarClientes()
  }, [])

  async function carregarClientes() {
    try {
      const res = await fetch(`${API_URL}/clientes`)
      if (res.ok) {
        setClientes(await res.json())
      }
    } catch {
      // silently ignore when API is not available
    }
  }

  function validar() {
    const e = {}
    if (!form.nome.trim()) e.nome = 'Nome é obrigatório'
    if (!form.sobrenome.trim()) e.sobrenome = 'Sobrenome é obrigatório'
    if (!form.email.trim() || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email))
      e.email = 'E-mail inválido'
    if (!form.celular.trim() || !/^\d{10,11}$/.test(form.celular))
      e.celular = 'Celular deve conter 10 ou 11 dígitos (apenas números)'
    if (!form.cep.trim() || !/^\d{8}$/.test(form.cep))
      e.cep = 'CEP deve conter 8 dígitos (apenas números)'
    return e
  }

  async function handleSubmit(e) {
    e.preventDefault()
    const erros = validar()
    if (Object.keys(erros).length > 0) {
      setErrors(erros)
      return
    }
    setErrors({})

    try {
      const url = editingId ? `${API_URL}/clientes/${editingId}` : `${API_URL}/clientes`
      const method = editingId ? 'PUT' : 'POST'
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      })

      if (res.ok || res.status === 201) {
        setMensagem(editingId ? 'Cliente atualizado com sucesso!' : 'Cliente cadastrado com sucesso!')
        setForm(initialForm)
        setEditingId(null)
        carregarClientes()
      } else {
        const data = await res.json()
        setMensagem('Erro: ' + (data.message || 'Verifique os dados informados.'))
      }
    } catch {
      setMensagem('Erro ao conectar com a API.')
    }

    setTimeout(() => setMensagem(null), 4000)
  }

  function handleChange(e) {
    const { name, value } = e.target
    setForm(prev => ({ ...prev, [name]: value }))
    if (errors[name]) setErrors(prev => ({ ...prev, [name]: undefined }))
  }

  function handleEditar(cliente) {
    setForm({
      nome: cliente.nome,
      sobrenome: cliente.sobrenome,
      email: cliente.email,
      celular: cliente.celular,
      cep: cliente.cep,
    })
    setEditingId(cliente.id)
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  async function handleDeletar(id) {
    if (!confirm('Tem certeza que deseja excluir este cliente?')) return
    try {
      await fetch(`${API_URL}/clientes/${id}`, { method: 'DELETE' })
      carregarClientes()
    } catch {
      setMensagem('Erro ao excluir cliente.')
      setTimeout(() => setMensagem(null), 3000)
    }
  }

  function handleCancelar() {
    setForm(initialForm)
    setEditingId(null)
    setErrors({})
  }

  return (
    <div className="container">
      <h1>👤 Cadastro de Clientes</h1>

      {mensagem && (
        <div className={`mensagem ${mensagem.startsWith('Erro') ? 'erro' : 'sucesso'}`}>
          {mensagem}
        </div>
      )}

      <div className="form-card">
        <h2>{editingId ? 'Editar Cliente' : 'Novo Cliente'}</h2>
        <form onSubmit={handleSubmit} noValidate>
          <div className="field">
            <label htmlFor="nome">Nome *</label>
            <input
              id="nome"
              name="nome"
              type="text"
              placeholder="Digite o nome"
              value={form.nome}
              onChange={handleChange}
              className={errors.nome ? 'input-error' : ''}
            />
            {errors.nome && <span className="error-msg">{errors.nome}</span>}
          </div>

          <div className="field">
            <label htmlFor="sobrenome">Sobrenome *</label>
            <input
              id="sobrenome"
              name="sobrenome"
              type="text"
              placeholder="Digite o sobrenome"
              value={form.sobrenome}
              onChange={handleChange}
              className={errors.sobrenome ? 'input-error' : ''}
            />
            {errors.sobrenome && <span className="error-msg">{errors.sobrenome}</span>}
          </div>

          <div className="field">
            <label htmlFor="email">E-mail *</label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="exemplo@email.com"
              value={form.email}
              onChange={handleChange}
              className={errors.email ? 'input-error' : ''}
            />
            {errors.email && <span className="error-msg">{errors.email}</span>}
          </div>

          <div className="field">
            <label htmlFor="celular">Celular *</label>
            <input
              id="celular"
              name="celular"
              type="tel"
              placeholder="Ex: 11987654321"
              value={form.celular}
              onChange={handleChange}
              className={errors.celular ? 'input-error' : ''}
            />
            {errors.celular && <span className="error-msg">{errors.celular}</span>}
          </div>

          <div className="field">
            <label htmlFor="cep">CEP *</label>
            <input
              id="cep"
              name="cep"
              type="text"
              placeholder="Ex: 01310100"
              value={form.cep}
              onChange={handleChange}
              className={errors.cep ? 'input-error' : ''}
            />
            {errors.cep && <span className="error-msg">{errors.cep}</span>}
          </div>

          <div className="form-actions">
            <button type="submit" className="btn-primary">
              {editingId ? 'Atualizar' : 'Cadastrar'}
            </button>
            {editingId && (
              <button type="button" className="btn-secondary" onClick={handleCancelar}>
                Cancelar
              </button>
            )}
          </div>
        </form>
      </div>

      {clientes.length > 0 && (
        <div className="lista-card">
          <h2>Clientes Cadastrados</h2>
          {clientes.map(c => (
            <div key={c.id} className="cliente-card">
              <div className="cliente-info">
                <strong>{c.nome} {c.sobrenome}</strong>
                <span>{c.email}</span>
                <span>📱 {c.celular}</span>
                <span>📍 CEP: {c.cep}</span>
              </div>
              <div className="card-actions">
                <button className="btn-edit" onClick={() => handleEditar(c)}>Editar</button>
                <button className="btn-delete" onClick={() => handleDeletar(c.id)}>Excluir</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}

export default App
