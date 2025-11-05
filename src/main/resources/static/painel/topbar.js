/* ===========================================================
   🔹 TOPBAR.JS — Script global da barra superior
   =========================================================== */
document.addEventListener("DOMContentLoaded", () => {
  const btnModo = document.getElementById("btnModo");
  const icone = btnModo?.querySelector("i");
  const userMenu = document.getElementById("userMenuDropdown");

  /* ===========================================================
     🌙 Alternância de Tema (Escuro/Claro)
     =========================================================== */
  const temaSalvo = localStorage.getItem("temaSelecionado") || "light";
  document.body.classList.add(`${temaSalvo}-mode`);

  if (icone) {
    icone.className = temaSalvo === "dark" ? "bi bi-sun" : "bi bi-moon-stars";
  }

  btnModo?.addEventListener("click", () => {
    const modoAtual = document.body.classList.contains("dark-mode") ? "dark" : "light";
    const novoModo = modoAtual === "dark" ? "light" : "dark";

    document.body.classList.remove(`${modoAtual}-mode`);
    document.body.classList.add(`${novoModo}-mode`);

    if (icone) {
      icone.className = novoModo === "dark" ? "bi bi-sun" : "bi bi-moon-stars";
    }

    localStorage.setItem("temaSelecionado", novoModo);
  });

  /* ===========================================================
     👤 Menu Admin (Logout Simples)
     =========================================================== */
  userMenu?.addEventListener("click", () => {
    const confirmar = confirm("Deseja realmente encerrar a sessão?");
    if (confirmar) {
      localStorage.removeItem("clienteLogado");
      alert("Sessão encerrada com sucesso!");
      const ctx = "/" + location.pathname.split("/")[1];
      window.location.href = `${ctx}/loginAcesso.html`;
    }
  });

  /* ===========================================================
     🔹 Destaque de Ícone Ativo
     =========================================================== */
  const caminhoAtual = location.pathname.split("/").pop();
  document.querySelectorAll(".nav-item").forEach(link => {
    const href = link.getAttribute("href");
    if (href && href.includes(caminhoAtual)) {
      link.classList.add("active");
    }
  });

  /* ===========================================================
     💚 Botão de Copiar Link do Agendamento (WhatsApp)
     =========================================================== */
  const btnCopiarLinkTopo = document.getElementById("btnCopiarLinkTopo");
  if (btnCopiarLinkTopo) {
    btnCopiarLinkTopo.innerHTML = '<i class="bi bi-whatsapp"></i>'; // Ícone inicial fixo

    btnCopiarLinkTopo.addEventListener("click", async () => {
      const baseUrl = window.location.origin;
      const linkAgendamento = `${baseUrl}/painel/agendamentoServico.html`;

      try {
        await navigator.clipboard.writeText(linkAgendamento);

        // ✅ Ícone de confirmação
        btnCopiarLinkTopo.innerHTML = '<i class="bi bi-check-circle"></i>';
        btnCopiarLinkTopo.style.backgroundColor = "#128C7E";

        // Retorna ao ícone original
        setTimeout(() => {
          btnCopiarLinkTopo.innerHTML = '<i class="bi bi-whatsapp"></i>';
          btnCopiarLinkTopo.style.backgroundColor = "#25D366";
        }, 2000);

        alert("✅ Link de agendamento copiado!\nEnvie pelo WhatsApp, Instagram ou Facebook.");
      } catch (err) {
        console.error("Erro ao copiar:", err);
        alert("⚠️ Não foi possível copiar automaticamente. Copie manualmente:\n" + linkAgendamento);
      }
    });
  }

  /* ===========================================================
     🎨 ESTILO GLOBAL — Botão WhatsApp com animação Pulse
     =========================================================== */
  const estiloWhats = document.createElement("style");
  estiloWhats.innerHTML = `
    /* 💚 Botão WhatsApp fixo com efeito pulse */
    #btnCopiarLinkTopo {
      background-color: #25d366 !important;
      border: none;
      color: white;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: transform 0.2s ease, box-shadow 0.2s ease, filter 0.2s ease;
    }

    #btnCopiarLinkTopo:hover {
      transform: scale(1.1);
      box-shadow: 0 0 10px rgba(37, 211, 102, 0.5);
    }

    @keyframes pulseWhats {
      0% { transform: scale(1); filter: brightness(1); }
      50% { transform: scale(1.25); filter: brightness(1.5); }
      100% { transform: scale(1); filter: brightness(1); }
    }

    #btnCopiarLinkTopo:hover i {
      animation: pulseWhats 0.6s ease;
      color: #fff;
      text-shadow: 0 0 8px rgba(255, 255, 255, 0.6),
                   0 0 12px rgba(37, 211, 102, 0.6);
    }
  `;
  document.head.appendChild(estiloWhats);
});
